package com.aachaerandio.postsdemo;

import com.aachaerandio.postsdemo.domain.GetComments;
import com.aachaerandio.postsdemo.domain.GetUserName;
import com.aachaerandio.postsdemo.model.Comment;
import com.aachaerandio.postsdemo.model.Post;
import com.aachaerandio.postsdemo.model.User;
import com.aachaerandio.postsdemo.presenter.PostDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostDetailsPresenterTest {

    private static final Integer USER_ID = 5;
    private static final Integer POST_ID = 1;
    private static final Integer COMMENT_ID = 2;

    private PostDetailsPresenter postDetailsPresenter;
    @Mock
    private PostDetailsPresenter.UserInterface mockPostDetailView;
    @Mock
    private GetUserName mockGetUser;
    @Mock
    private GetComments mockGetComments;

    @Before
    public void setup() {
        postDetailsPresenter = new PostDetailsPresenter();
        postDetailsPresenter.setView(mockPostDetailView);
        postDetailsPresenter.setGetUserName(mockGetUser);
        postDetailsPresenter.setGetComments(mockGetComments);
    }

    @Test
    public void givenPostDetailsLoaded_WhenInitialising_ThenShowDetails() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((FinishedInterface)invocation.getArguments()[1]).onFinished(getMockUserResponse());

                return null;
            }
        }).when(mockGetUser).execute(ArgumentMatchers.eq(USER_ID), any(FinishedInterface.class));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((FinishedInterface)invocation.getArguments()[1]).onFinished(getMockComments());

                return null;
            }
        }).when(mockGetComments).execute(ArgumentMatchers.eq(POST_ID), any(FinishedInterface.class));

        postDetailsPresenter.loadDetails(createPost());

        ArgumentCaptor<Post> ac = ArgumentCaptor.forClass(Post.class);
        ArgumentCaptor<User> ac2 = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Integer> ac3 = ArgumentCaptor.forClass(Integer.class);
        verify(mockPostDetailView, times(1)).showDetails(ac.capture(), ac2.capture(), ac3.capture());

        assertEquals(ac.getValue().getId(), POST_ID);
        assertEquals(ac2.getValue().getId(), USER_ID);
        assertEquals(ac3.getValue(), new Integer(1));
    }

    private User getMockUserResponse() {
        User user = new User();
        user.setId(USER_ID);
        return user;
    }

    private Post createPost() {
        Post post = new Post();
        post.setId(POST_ID);
        post.setUserId(USER_ID);
        return post;
    }

    private List<Comment> getMockComments() {
        Comment comment = new Comment();
        comment.setId(COMMENT_ID);
        return Arrays.asList(comment);
    }
}
