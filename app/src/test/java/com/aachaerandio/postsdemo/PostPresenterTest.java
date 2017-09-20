package com.aachaerandio.postsdemo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.aachaerandio.postsdemo.domain.GetPostList;
import com.aachaerandio.postsdemo.model.Post;
import com.aachaerandio.postsdemo.presenter.PostsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostPresenterTest {

    private PostsPresenter postsPresenter;
    @Mock
    private Context mockContext;
    @Mock
    private PostsPresenter.UserInterface mockPostListView;
    @Mock
    private GetPostList mockGetPostList;

    @Before
    public void setup(){
        postsPresenter = new PostsPresenter();
        postsPresenter.setView(mockPostListView);
        postsPresenter.setGetPostList(mockGetPostList);
    }

    @Test
    public void givenPostsLoaded_WhenInitialising_ThenShowPosts() throws Exception {

        //Given
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((FinishedInterface)invocation.getArguments()[0]).onFinished(getMockPostResponse());

                return null;
            }
        }).when(mockGetPostList).execute(any(FinishedInterface.class));

        //When
        postsPresenter.loadPosts();

        //Then
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(mockPostListView, times(1)).showPosts(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().size(), 1);
        assertEquals(((Post)argumentCaptor.getValue().get(0)).getId(), new Integer(1));
    }

    @NonNull
    private List<Post> getMockPostResponse() {
        List<Post> stub = new ArrayList<>();
        Post post = new Post();
        post.setTitle("TestTitle");
        post.setBody("TestBody");
        post.setId(1);

        stub.add(post);
        return stub;
    }

    @Test
    public void givenLoadPostsFails_WhenInitialising_ThenShowError() throws Exception {

    }
}
