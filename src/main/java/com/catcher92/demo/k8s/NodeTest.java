package com.catcher92.demo.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.util.Config;

import java.io.IOException;

public class NodeTest {

    private static final String master = "https://172.16.89.6:6443";
    private static final String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InFoaVR0dm4zZzM0YlA1UUVyNXo3YjA5cVo0bkVfVmlVZUFNZjVCYVkyNGsifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJuYW1lc3BhY2UtY29udHJvbGxlci10b2tlbi1ra241dyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJuYW1lc3BhY2UtY29udHJvbGxlciIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjRhNzYwZDhhLTIxZjYtNGE0MC1hZjczLTYyYmIxOWNjZGU3MiIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlLXN5c3RlbTpuYW1lc3BhY2UtY29udHJvbGxlciJ9.fdQpxxXVuu3KnUJrNZbQq-_XIGjaSEVX6OrSVvf5FTRTV01XrG_OkKqcq47FhpoDZjmdYrNf2qGe7SYpZ9wRM_9TX5XZkBfLgioZwdQVCIhA35B8XSRpybtDSYm1KYDIkReFlFvYOmlroYxxoZG5rv06OKQA8eeOyaJCcXZxEnfpcDwXOyT9F4eqqoHTazGXrvOJ2rCRWaGTxN7MVn2-fSeY-fhuEFSueH5ggZPWBg1pP_LR9ibyOxuXfyW2SQ9deYnftXaNY7-92ifRfn-aFZ9uGEzm-U8_96cEoKqWe9H-fSgLAZNP5VgVbqeIYXBOnvzUqfODsbs75xrovAZIow";

    public static void main(String[] args) throws IOException, ApiException {
        ApiClient client = Config.fromToken(master, token).setVerifyingSsl(false);
        CoreV1Api api = new CoreV1Api(client);
        final V1NodeList v1NodeList = api.listNode(null, true, null, null, null, -1, null, 3000, false);
        for (V1Node v1Node : v1NodeList.getItems()) {
            System.out.println(v1Node);
        }
    }

}
