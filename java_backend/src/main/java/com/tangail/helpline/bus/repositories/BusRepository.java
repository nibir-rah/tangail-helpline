package com.tangail.helpline.bus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.bus.entity.BusEntity;
import com.tangail.helpline.bus.enums.RouteTypeEnum;

@Repository
public interface BusRepository extends JpaRepository<BusEntity, Long> {
    List<BusEntity> findByRouteTypeAndDestinationId(RouteTypeEnum routeTypeEnum, Long destId);
}
