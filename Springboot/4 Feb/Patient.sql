CREATE TABLE Patient (
    p_id SERIAL PRIMARY KEY,
    info JSONB
);

select * from Patient;