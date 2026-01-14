CREATE TABLE customization (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title TEXT NOT NULL,
    customization_type TEXT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO customization (title, customization_type, is_active) VALUES
('green','THEME', TRUE),
('orange','THEME',FALSE),
('blue', 'THEME', FALSE),
('midnight','THEME', FALSE),
('shape_1', 'BACKGROUND', TRUE),
('shape_2', 'BACKGROUND', FALSE),
('shape_3', 'BACKGROUND', FALSE),
('shape_4', 'BACKGROUND', FALSE),
('Barlow', 'FONT', TRUE),
('TeXGyteCursor', 'FONT', FALSE),
('namu', 'FONT', FALSE),
('Valisca', 'FONT', FALSE),
('Plants Market', 'SITE_NAME', TRUE);