package tacos.repositoryes.order;

import tacos.models.TacoOrder;

public interface OrderRepository {
	TacoOrder save(TacoOrder order);
}
