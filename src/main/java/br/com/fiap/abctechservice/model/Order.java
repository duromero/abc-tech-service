package br.com.fiap.abctechservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Assistance> services;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name="start_order_location_id", foreignKey = @ForeignKey( name = "FK_start_order_id"))
    private OrderLocation startOrderLocation;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name="end_order_location_id", foreignKey = @ForeignKey(name = "FK_end_order_id"))
    private OrderLocation endtOrderLocation;

    public boolean hasMinAssists(){
        return this.services.size()>0;
    }

    public boolean exceedsMaxAssists(){
        return this.services.size()>15;
    }
}
