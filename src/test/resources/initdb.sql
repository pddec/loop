CREATE TABLE public.bill (
	id bigint NOT NULL,
	bill_name character varying(32) NOT NULL,
	"VALUE" NUMERIC NOT NULL,
	due timestamp without time zone,
	create_ts timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_ts timestamp without time zone
); 

ALTER TABLE public.bill
	ADD CONSTRAINT IF NOT EXISTS bill_id_pkey PRIMARY KEY (id);
	
CREATE SEQUENCE  IF NOT EXISTS public.update_test_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.bill ALTER COLUMN id SET DEFAULT nextval('public.update_test_id_seq');

CREATE TRIGGER update_timestamp_trigger
BEFORE UPDATE
ON public.bill
FOR EACH ROW
CALL "loop.update.trigger.UpdateTimestamp";

CREATE ALIAS now_plus FOR "loop.update.utils.Functions.now_plus";

INSERT INTO public.bill (bill_name,"VALUE",due) VALUES 
	('NETFLIX',10.00,now_plus(30,'DAYS')),
	('CREDITCARD',2000.0,now_plus(30,'DAYS'));
	
UPDATE public.bill b SET 
	"VALUE" = 2800.00
WHERE b.id = 2;
	