package simon.chareyron.tennis.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.mapper.mapstruct.TennisScoreMapStrutMapperImpl;
import simon.chareyron.tennis.usecase.impl.SimulateTennisMatchUseCaseImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class LambdaRequestStreamHandler implements RequestStreamHandler {

    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream, Context context) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        new TennisMatchSimulatorControllerImpl
                (new SimulateTennisMatchUseCaseImpl(
                        new TennisScoreMapStrutMapperImpl()
                )).simulateTennisMatch(2)
                .doOnNext(score -> {
                    try {
                        outputStream.write(gson.toJson(score).getBytes(Charset.forName("UTF-8")));
                    } catch (IOException ioe) {
                        ioe.printStackTrace(System.err);
                    }
                })
                .blockLast();
    }
}
