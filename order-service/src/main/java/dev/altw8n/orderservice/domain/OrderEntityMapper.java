package dev.altw8n.orderservice.domain;

import dev.altw8n.orderservice.api.OrderDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OrderEntityMapper {
    OrderEntity toEntity(OrderDto orderDto);
//
//    @AfterMapping
//    default void linkOrderItemEntities(@MappingTarget OrderEntity orderEntity) {
//        orderEntity
//                .getItems()
//                .forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));
//    }

    OrderDto toOrderDto(OrderEntity orderEntity);
}
