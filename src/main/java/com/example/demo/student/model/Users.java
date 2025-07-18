    package com.example.demo.student.model;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "users")
    public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String username;
        private String password;

        public Users(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public Users() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
