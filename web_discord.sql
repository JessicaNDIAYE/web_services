-- Création de la table Utilisateur
CREATE TABLE Utilisateur (
    id_utilisateur SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255),
    mot_de_passe VARCHAR(255)
);

-- Création de la table Espace_de_travail
CREATE TABLE Espace_de_travail (
    id_espace_de_travail SERIAL PRIMARY KEY,
    nom_espace_de_travail VARCHAR(255)
);

-- Création de la table Administrer (relation n-n)
CREATE TABLE Administrer (
    id_utilisateur INTEGER,
    id_espace_de_travail INTEGER,
    PRIMARY KEY (id_utilisateur, id_espace_de_travail),
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE,
    FOREIGN KEY (id_espace_de_travail) REFERENCES Espace_de_travail(id_espace_de_travail) ON DELETE CASCADE
);

-- Création de la table Channel
CREATE TABLE Channel (
    id_channel SERIAL PRIMARY KEY,
    topic TEXT,
    id_espace_de_travail INTEGER,
    FOREIGN KEY (id_espace_de_travail) REFERENCES Espace_de_travail(id_espace_de_travail) ON DELETE CASCADE
);

-- Création de la table Message
CREATE TABLE Message (
    id_message SERIAL PRIMARY KEY,
    contenu TEXT,
    date_et_heure TIMESTAMP,
    id_utilisateur INTEGER,
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE SET NULL
);

-- Création de la table Publier (relation n-n)
CREATE TABLE Publier (
    id_message INTEGER,
    id_channel INTEGER,
    PRIMARY KEY (id_message, id_channel),
    FOREIGN KEY (id_message) REFERENCES Message(id_message) ON DELETE CASCADE,
    FOREIGN KEY (id_channel) REFERENCES Channel(id_channel) ON DELETE CASCADE
);

-- Création de la table Réagir (relation n-n avec attribut date)
CREATE TABLE Reagir (
    id_utilisateur INTEGER,
    id_message INTEGER,
    PRIMARY KEY (id_utilisateur, id_message),
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE,
    FOREIGN KEY (id_message) REFERENCES Message(id_message) ON DELETE CASCADE
);

-- Création de la table Envoyer (relation n-n implicite pour les messages directs)
CREATE TABLE Envoyer (
    id_utilisateur INTEGER,
    id_message INTEGER,
    PRIMARY KEY (id_utilisateur, id_message),
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE,
    FOREIGN KEY (id_message) REFERENCES Message(id_message) ON DELETE CASCADE
);
