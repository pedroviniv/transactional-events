/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events.demo;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Order {
    
    private String id;
    private String status;
    private String client;

    public static class Builder {

        private String id;
        private String status;
        private String client;

        private Builder() {
        }

        public Builder id(final String value) {
            this.id = value;
            return this;
        }

        public Builder status(final String value) {
            this.status = value;
            return this;
        }

        public Builder client(final String value) {
            this.client = value;
            return this;
        }

        public Order build() {
            return new io.github.kieckegard.transactional.events.demo.Order(id, status, client);
        }
    }

    public static Order.Builder builder() {
        return new Order.Builder();
    }

    private Order(final String id, final String status, final String client) {
        this.id = id;
        this.status = status;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", status=" + status + ", client=" + client + '}';
    }
}
