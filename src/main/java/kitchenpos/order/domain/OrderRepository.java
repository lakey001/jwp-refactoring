package kitchenpos.order.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
	boolean existsByOrderTable_IdAndOrderStatusIn(Long orderTableId, List<OrderStatus> orderStatuses);
	boolean existsByOrderTable_IdInAndOrderStatusIn(List<Long> orderTableIds, List<OrderStatus> orderStatuses);
}
