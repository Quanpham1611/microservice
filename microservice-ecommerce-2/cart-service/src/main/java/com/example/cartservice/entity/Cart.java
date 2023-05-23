package com.example.cartservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDate updatedAt;

    public void addItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void removeItem(Long itemId) {
        this.cartItems.removeIf(item -> item.getId().equals(itemId));
    }
}
