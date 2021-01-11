package server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
       //extract fields from the request
        Greeting greeting = request.getGreeting();


       String firstName = greeting.getFirstName();
       String lastName = greeting.getLastName();

       //create the response
       String result = "hello " + firstName + " " + lastName;

       GreetResponse greetResponse = GreetResponse.newBuilder()
               .setResult(result)
               .build();

       //send the response
       responseObserver.onNext(greetResponse);

       //complete the RPC call

        responseObserver.onCompleted();
    }
}
