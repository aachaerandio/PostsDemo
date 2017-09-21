package com.aachaerandio.postsdemo;

import com.aachaerandio.postsdemo.domain.GetPostList;
import com.aachaerandio.postsdemo.model.Post;
import com.aachaerandio.postsdemo.service.PostApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockSettings;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetPostListTest {

    private GetPostList getPostList;
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
        getPostList = new GetPostList();
        getPostList.setPostApiService(mockPostApiService);

        when(mockPostApiService.getService()).thenReturn(mockPostService);

        when(mockPostService.getAllPosts()).thenReturn(mockCall);
    }

    @Test
    public void givenPostListLoaded() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback)invocation.getArguments()[0]).onResponse(Mockito.mock(Call.class), getMockSuccessfulResponse());

                return null;
            }
        }).when(mockCall).enqueue(any(Callback.class));

        getPostList.execute(mockFinishedInterface);

        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(mockFinishedInterface, times(1)).onFinished(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().size(), 1);
        assertEquals(((Post)argumentCaptor.getValue().get(0)).getId(), new Integer(1));
    }

    @Test
    public void givenLoadPostListFails() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback)invocation.getArguments()[0]).onFailure(null, null);

                return null;
            }
        }).when(mockCall).enqueue(any(Callback.class));

        getPostList.execute(mockFinishedInterface);

        verify(mockFinishedInterface, times(1)).onError();
    }

    private Response getMockSuccessfulResponse() {
        Post post = new Post();
        post.setId(1);
        return Response.success(Arrays.asList(post));
    }

}
