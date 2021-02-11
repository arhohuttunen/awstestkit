package example;

// tag::user_guide[]
import com.github.awstestkit.Endpoint;
import com.github.awstestkit.EndpointResolver;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.net.URI;
import java.net.URISyntaxException;

public class MyEndpointResolver implements EndpointResolver {
    @NotNull
    @Override
    public Endpoint resolveEndpoint(@NotNull ExtensionContext extensionContext) {
        return new Endpoint() {
            @NotNull
            @Override
            public URI url() throws URISyntaxException {
                return new URI(System.getenv("AWS_URL"));
            }

            @NotNull
            @Override
            public String region() {
                return System.getenv("AWS_REGION");
            }

            @NotNull
            @Override
            public String accessKey() {
                return System.getenv("AWS_ACCESS_KEY");
            }

            @NotNull
            @Override
            public String secretKey() {
                return System.getenv("AWS_SECRET_KEY");
            }
        };
    }
}
// end::user_guide[]
