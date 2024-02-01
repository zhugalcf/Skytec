CREATE TABLE transactions (
    id bigint auto_increment PRIMARY KEY,
    initiator_id bigint NOT NULL,
    clan_id bigint NOT NULL,
    action_type varchar(64) NOT NULL,
    old_gold bigint NOT NULL,
    new_gold bigint NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);




