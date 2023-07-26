package DemoSpringMVC.demo.service.order;

import DemoSpringMVC.demo.domain.cart.Checkout;
import DemoSpringMVC.demo.domain.order.Order;
import DemoSpringMVC.demo.domain.orderDetail.OrderDetail;
import DemoSpringMVC.demo.entity.OrderDetailEntity;
import DemoSpringMVC.demo.entity.OrderEntity;
import DemoSpringMVC.demo.repository.OrderRepository;
import DemoSpringMVC.demo.service.orderDetail.IOrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, Order.class))
                .toList();
    }

    @Override
    public Order getById(long id) {
        return modelMapper.map(orderRepository.findById(id).get(), Order.class);
    }

    @Override
    public void create(Checkout checkout, List<OrderDetail> cartList) {
        OrderEntity orderEntity = orderRepository.save(handleConvertCheckoutToOrderEntity(checkout));
        handleConvertListOrderDetailToListOrderDetailEntity(cartList)
                .stream()
                .forEach(item -> {
                    item.setOrder(orderEntity);
                    orderDetailService.create(item);
                });
    }

    private OrderEntity handleConvertCheckoutToOrderEntity(Checkout checkout){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDate(new Date());
        orderEntity.setCustomerName(checkout.getCustomerName());
        orderEntity.setCustomerAddress(checkout.getCustomerAddress());
        return orderEntity;
    }

    private List<OrderDetailEntity> handleConvertListOrderDetailToListOrderDetailEntity(List<OrderDetail> cartList){
        return cartList.stream()
                .map(item -> modelMapper.map(item, OrderDetailEntity.class))
                .toList();
    }
}
