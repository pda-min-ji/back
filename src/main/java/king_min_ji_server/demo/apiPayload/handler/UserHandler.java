package king_min_ji_server.demo.apiPayload.handler;


import king_min_ji_server.demo.apiPayload.GeneralException;
import king_min_ji_server.demo.apiPayload.code.BaseErrorCode;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
