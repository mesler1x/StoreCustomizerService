CREATE TABLE theme (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title TEXT NOT NULL,
    metadata TEXT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO theme (title, metadata, is_active) VALUES
('Green theme', 'metadata for frontend', TRUE),
('Orange theme', 'metadata for frontend', FALSE),
('Blue theme', 'metadata for frontend', FALSE),
('Midnight theme', 'metadata for frontend', FALSE);

