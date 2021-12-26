package kitchenpos.tobe.orders.dto.ordertable;

import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.tobe.orders.domain.ordertable.OrderTable;

public class OrderTableResponse {

    private final Long id;

    private final Long tableGroupId;

    private final int numberOfGuests;

    private final boolean empty;

    public OrderTableResponse(
        final Long id,
        final Long tableGroupId,
        final int numberOfGuests,
        final boolean empty
    ) {
        this.id = id;
        this.tableGroupId = tableGroupId;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public static List<OrderTableResponse> ofList(final List<OrderTable> orderTables) {
        return orderTables.stream()
            .map(OrderTableResponse::of)
            .collect(Collectors.toList());
    }

    public static OrderTableResponse of(final OrderTable orderTable) {
        return new OrderTableResponse(
            orderTable.getId(),
            orderTable.getTableGroupId(),
            orderTable.getNumberOfGuests(),
            orderTable.isEmpty()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }
}
