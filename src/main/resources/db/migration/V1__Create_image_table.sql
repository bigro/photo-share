CREATE TABLE image(
  public_id    VARCHAR    NOT NULL,
  created_at   TIMESTAMP  NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (public_id)
);