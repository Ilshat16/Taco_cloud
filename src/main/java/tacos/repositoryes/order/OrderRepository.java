package tacos.repositoryes.order;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import tacos.models.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID>{
}
