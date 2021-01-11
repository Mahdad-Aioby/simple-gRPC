package client;

import com.proto.dummy.Dummy;
import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import server.GreetServiceImpl;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("hello clien");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",5005)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub syncGreetClient =
                GreetServiceGrpc.newBlockingStub(channel);

        //created a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Mahdad")
                .setLastName("Aioby")
                .build();

        //request
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        //its not a direct call
        //call and get response
        GreetResponse response = syncGreetClient.greet(greetRequest);

        System.out.println(response.getResult());

        channel.shutdown();

    }
}
