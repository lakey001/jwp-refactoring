package kitchenpos.tobe.orders.application.ordertable;

import java.util.List;
import kitchenpos.tobe.common.domain.Validator;
import kitchenpos.tobe.orders.domain.ordertable.OrderTable;
import kitchenpos.tobe.orders.domain.ordertable.OrderTableRepository;
import kitchenpos.tobe.orders.dto.ordertable.OrderTableChangeEmptyRequest;
import kitchenpos.tobe.orders.dto.ordertable.OrderTableChangeNumberOfGuestsRequest;
import kitchenpos.tobe.orders.dto.ordertable.OrderTableResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderTableService {

    private final OrderTableRepository orderTableRepository;
    private final Validator<OrderTable> validator;

    public OrderTableService(
        final OrderTableRepository orderTableRepository,
        final Validator<OrderTable> validator
    ) {
        this.orderTableRepository = orderTableRepository;
        this.validator = validator;
    }

    @Transactional
    public OrderTableResponse create() {
        final OrderTable orderTable = orderTableRepository.save(new OrderTable());
        return OrderTableResponse.of(orderTable);
    }

    @Transactional(readOnly = true)
    public List<OrderTableResponse> list() {
        return OrderTableResponse.ofList(orderTableRepository.findAll());
    }

    @Transactional
    public OrderTableResponse changeEmpty(
        final Long orderTableId,
        final OrderTableChangeEmptyRequest request
    ) {
        final OrderTable orderTable = orderTableRepository.findById(orderTableId)
            .orElseThrow(IllegalArgumentException::new);
        if (request.isEmpty()) {
            orderTable.clear(validator);
        }
        if (!request.isEmpty()) {
            orderTable.serve();
        }
        return OrderTableResponse.of(orderTable);
    }

    @Transactional
    public OrderTableResponse changeNumberOfGuests(
        final Long orderTableId,
        final OrderTableChangeNumberOfGuestsRequest request
    ) {
        final OrderTable orderTable = orderTableRepository.findById(orderTableId)
            .orElseThrow(IllegalArgumentException::new);
        orderTable.changeNumberOfGuests(request.getNumberOfGuests());
        return OrderTableResponse.of(orderTable);
    }
}
