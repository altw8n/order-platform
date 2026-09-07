package dev.altw8n.orderservice.domain;

import dev.altw8n.orderservice.api.CreateOrderRequestDto;
import dev.altw8n.orderservice.api.OrderDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OrderEntityMapper {
    OrderEntity toEntity(CreateOrderRequestDto requestDto);

    OrderDto toOrderDto(OrderEntity orderEntity);
}
