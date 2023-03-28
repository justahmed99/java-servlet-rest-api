DROP TABLE IF EXISTS "items";
CREATE TABLE "public"."items"
(
    "id" character varying(36) primary key ,
    "name" character varying(50) not null ,
    "item_number" int not null ,
    "price" bigint not null ,
    "is_active" boolean default true not null
);