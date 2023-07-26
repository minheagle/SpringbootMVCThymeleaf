package DemoSpringMVC.demo.service.orderDetail;

import DemoSpringMVC.demo.domain.orderDetail.OrderDetail;
import DemoSpringMVC.demo.entity.OrderDetailEntity;
import DemoSpringMVC.demo.entity.OrderEntity;
import DemoSpringMVC.demo.repository.OrderDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, OrderDetail.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getAllByOrderId(long orderId) {
        return orderDetailRepository.findAllByOrderId(orderId)
                .stream()
                .map(item -> modelMapper.map(item, OrderDetail.class))
                .toList();
    }

    @Override
    public void create(OrderDetailEntity orderDetailEntity) {
        orderDetailRepository.save(orderDetailEntity);
    }

    @Override
    public void update(long id, OrderDetail orderDetail) {

    }

    @Override
    public void delete(long id) {

    }
}
