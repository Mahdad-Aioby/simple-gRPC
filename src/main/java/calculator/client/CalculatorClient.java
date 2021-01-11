package calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",5005)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorServiceBlockingStub = CalculatorServiceGrpc.newBlockingStub(channel);
        SumRequest request = SumRequest.newBuilder()
                .setFirstNumber(3)
                .setSecondNumber(7).build();

       SumResponse response = calculatorServiceBlockingStub.sum(request);

        System.out.println(response.getSumResult());

        channel.shutdown();
    }
}
