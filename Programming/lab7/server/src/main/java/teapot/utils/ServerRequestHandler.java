package teapot.utils;

import teapot.models.CommandRequest;
import teapot.models.Response;
import teapot.utils.enums.ResponseCode;
import teapot.utils.handlers.RequestHandler;

public class ServerRequestHandler implements RequestHandler {

    @Override
    public void sayHello() {
        System.out.println(
                """

                                         __          __
                        .----.-----.----|  |--.-----|  |_    .-----.-----.----.--.--.-----.----.
                        |   _|  _  |  __|    <|  -__|   _|   |__ --|  -__|   _|  |  |  -__|   _|
                        |__| |_____|____|__|__|_____|____|   |_____|_____|__|  \\___/|_____|__|


                                                            by Gleb Kiva


                                        Type (helpc) to list all command descriptions
                                        and parameter types they accept. Parameters
                                        should be entered on the same line as command,
                                        separated by space.
                        """);
    }

    @Override
    public Response process(CommandRequest request) {
        return new Response().setCode(ResponseCode.NOT_RECEIVED);
    }
}
