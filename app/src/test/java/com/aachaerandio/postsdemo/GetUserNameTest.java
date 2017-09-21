package com.aachaerandio.postsdemo;

import com.aachaerandio.postsdemo.domain.GetUserName;
import com.aachaerandio.postsdemo.model.User;
import com.aachaerandio.postsdemo.service.PostApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUserNameTest {

    public static final int USER_ID = 1;
    private GetUserName getUserName;
    @Mock
    private PostApiService mockPostApiService;
    @Mock
    private FinishedInterface mockFinishedInterface;
    @Mock
    private PostApiService.PostService mockPostService;
    @Mock
    private Call mockCall;

    @Before
    public void setup() {
        getUserName = new GetUserName();
        getUserName.setPostApiService(mockPostApiService);

        when(mockPostApiService.getService()).thenReturn(mockPostService);

        when(mockPostService.getUserById(USER_ID)).thenReturn(mockCall);
    }

    @Test
    public void givenUserNameLoaded() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback)invocation.getArguments()[0]).onResponse(Mockito.mock(Call.class), getMockSuccessfulResponse());

                return null;
            }
        }).when(mockCall).enqueue(any(Callback.class));

        getUserName.execute(USER_ID, mockFinishedInterface);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockFinishedInterface, times(1)).onFinished(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().getId(), new Integer(1));
    }

    @Test
    public void givenLoadUserNameFails() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback)invocation.getArguments()[0]).onFailure(null, null);

                return null;
            }
        }).when(mockCall).enqueue(any(Callback.class));

        getUserName.execute(USER_ID, mockFinishedInterface);

        verify(mockFinishedInterface, times(1)).onError();
    }


    private Response<User> getMockSuccessfulResponse() {
        User user = new User();
        user.setId(USER_ID);
        return Response.success(user);
    }
}
