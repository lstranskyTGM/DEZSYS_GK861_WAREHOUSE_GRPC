import com.google.common.collect.ImmutableList;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;

import warehouse.Warehouse;
import warehouse.WarehouseServiceGrpc;

/**
 * Implementation of the Warehouse grpc Service
 *
 * @author Leonhard Stransky
 * @version 2024-03-10
 */

public class WarehouseServiceImpl extends WarehouseServiceGrpc.WarehouseServiceImplBase {
    @Override
    public void getWarehouseData(Warehouse.WarehouseRequest request, StreamObserver<Warehouse.WarehouseResponse> responseObserver) {
        System.out.println("Handling warehouse endpoint" + request.toString());

        String warehouseUUID = request.getUuid();

        System.out.println("Getting data of warehouse with uuid=" + warehouseUUID + "...");

        // create a few dummy product objects

        Warehouse.Product product1 = Warehouse.Product.newBuilder()
                .setProductId("a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8")
                .setProductName("Apfel")
                .setProductCategory("Obst")
                .setProductQuantity(90)
                .setProductUnit("Kg")
                .build();
        Warehouse.Product product2 = Warehouse.Product.newBuilder()
                .setProductId("o9p8q7r6-s5t4-3210-v1w2-x3y4z5w6v7u8")
                .setProductName("Joghurt")
                .setProductCategory("Milchprodukte")
                .setProductQuantity(150)
                .setProductUnit("Stück")
                .build();
        Warehouse.Product product3 = Warehouse.Product.newBuilder()
                .setProductId("f1e2d3c4-b5a6-9870-v8w7-x6y5z4w3v2u1")
                .setProductName("Zwiebeln")
                .setProductCategory("Gemüse")
                .setProductQuantity(200)
                .setProductUnit("500g Beutel")
                .build();


        // now create the warehouse response object
        Warehouse.WarehouseResponse response = Warehouse.WarehouseResponse.newBuilder()
                .setWarehouseId(warehouseUUID)
                .setWarehouseName("Wien Lagerhaus 1")
                .setWarehouseAddress("Lagerstraße 1")
                .setWarehousePostalCode(1200)
                .setWarehouseCity("Wien")
                .setWarehouseCountry("AUSTRIA")
                .setTimestamp(LocalDateTime.now().toString())
                .addAllProductData(ImmutableList.of(product1, product2, product3))
                .build();

        // send the response to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
