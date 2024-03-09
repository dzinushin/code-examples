--
create table users
(
    id uuid not null primary key default gen_random_uuid(),
    oid varchar not null,
    attrs jsonb,
    created_at timestamptz not null default now()
);

create unique index on users(oid);

--
create table sessions
(
    id uuid not null primary key,
    user_id uuid not null references users(id),
    token varchar not null,
    created_at timestamptz not null default now(),
    expired_at timestamptz not null,
    accessed_at timestamptz not null
);

