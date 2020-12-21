public class OrderAlreadyAddedException extends Exception{
    OrderAlreadyAddedException(String address){
        super("со столиком или адресатом уже связан заказ");
    }
}
