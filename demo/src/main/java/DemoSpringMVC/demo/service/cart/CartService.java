package DemoSpringMVC.demo.service.cart;

import DemoSpringMVC.demo.domain.cart.Cart;
import DemoSpringMVC.demo.domain.orderDetail.OrderDetail;
import DemoSpringMVC.demo.domain.product.Product;
import DemoSpringMVC.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService{
    @Autowired
    private Cart cart;

    @Autowired
    private IProductService productService;

    @Override
    public List<OrderDetail> getAll() {
        return cart.getCartList()
                .stream()
                .map(item -> {
                    item.setId(cart.getCartList().indexOf(item));
                    return item;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addItem(Product product) {
        checkCountStock(product.getId(), 1);
        cart.getCartList()
                .stream()
                .filter(item -> item.getProduct().getId() == product.getId())
                .findFirst()
                .ifPresentOrElse(item -> item.setQuantity(item.getQuantity() + 1), () -> {
                    OrderDetail newCart = new OrderDetail();
                    newCart.setProduct(product);
                    newCart.setQuantity(1);
                    newCart.setPrice(product.getPrice());
                    cart.getCartList().add(newCart);
                });
    }

    @Override
    public void updateQuantity(long productId, int quantity) {
        checkCountStock(productId, quantity);
        cart.getCartList()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .get()
                .setQuantity(quantity);
    }

    private void checkCountStock(long productId, int quantity){
        if(productService.getCountStock(productId) < quantity){
            throw new RuntimeException("Số lượng đã vượt quá số lượng hàng tồn kho !");
        }
    }

    @Override
    public void removeItem(long cartId) {
        cart.getCartList().remove((int) cartId);
    }

    @Override
    public void emptyCart() {
        cart.getCartList().clear();
    }

    @Override
    public int count() {
        return cart.getCartList().size();
    }

    @Override
    public double getTotalCost() {
        return cart.getTotal();
    }
}
