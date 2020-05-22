import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.budgetreal.entity.User;
import com.exercise.budgetreal.model.vo.UserSearchVO;
import com.exercise.budgetreal.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {
    @Autowired
    private UserService userService;
    //按条件查询用户
    @Test
    public void testGetUser(){
        Page<User> userPage = new Page<>(1, 10);
        String search= "{\"name\":\"admin2\",\"realName\":\"\",\"school\":\"\",\"profession\":\"\",\"roleName\":\"\"}";
        UserSearchVO  userSearchVO = JSONObject.parseObject(search, UserSearchVO.class);
        IPage user = userService.getUser(userPage, userSearchVO);
        System.out.println(user.toString());
    }
}
