package com.aachaerandio.postsdemo;

import com.aachaerandio.postsdemo.domain.GetComments;
import com.aachaerandio.postsdemo.model.Comment;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class GetCommentsTest {

    public static final int POST_ID = 1;
    public static final int COMMENT_ID = 2;
    private GetComments getComments;
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
        getComments = new GetComments();
        getComments.setPostApiService(mockPostApiService);

        when(mockPostApiService.getService()).thenReturn(mockPostService);

        when(mockPostService.getCommentsByPostId(POST_ID)).thenReturn(mockCall);
    }

    @Test
    public void givenCommentsLoaded() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback)invocation.getArguments()[0]).onResponse(Mockito.mock(Call.class), getMockSuccessfulResponse());

                return null;
            }
        }).when(mockCall).enqueue(any(Callback.class));

        getComments.execute(POST_ID, mockFinishedInterface);

        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(mockFinishedInterface, times(1)).onFinished(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().size(), 1);
        assertEquals(((Comment)argumentCaptor.getValue().get(0)).getPostId(), new Integer(1));
    }

    @Test
    public void givenLoadCommentsFails() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback)invocation.getArguments()[0]).onFailure(null, null);

                return null;
            }
        }).when(mockCall).enqueue(any(Callback.class));

        getComments.execute(POST_ID, mockFinishedInterface);

        verify(mockFinishedInterface, times(1)).onError();
    }


    private Response<List<Comment>> getMockSuccessfulResponse() {
        Comment comment = new Comment();
        comment.setId(COMMENT_ID);
        comment.setPostId(POST_ID);
        return Response.success(Arrays.asList(comment));
    }
}
