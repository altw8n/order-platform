package dev.altw8n.orderservice.domain.db;

import dev.altw8n.api.http.order.CreateOrderRequestDto;
import dev.altw8n.api.http.order.OrderDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OrderEntityMapper {
    OrderEntity toEntity(CreateOrderRequestDto requestDto);

    OrderDto toOrderDto(OrderEntity orderEntity);
}
