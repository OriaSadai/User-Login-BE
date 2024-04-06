package com.userLogin.service;
import com.userLogin.model.User;
import com.userLogin.repository.UserRepository;
import com.userLogin.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public void createUser(User user) throws Exception {
        User existingUser = userRepository.findUserByUsername(user.getUsername());
        if(existingUser != null) {
            throw new Exception("username " + user.getUsername() + " is already taken");
        }
        logger.info(String.format("username \"%s\" is available. will create",user.getUsername()));
        userRepository.createUser(user);
    }
    @Override
    public User getUserByAuthorization (String authorization) {
        String jwt = authorization.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        return findUserByUsername(username);
    }
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Override
    public String getUserAddress(String authorization) {
        User user = getUserByAuthorization(authorization);
        return user.getAddress();
    }
    @Override
    public void removeUser(String authorization) {
        User userToRemove = getUserByAuthorization(authorization);
        if (userToRemove != null) {
            favoriteService.removeAllFavoriteItemsByUser(userToRemove);
            orderService.removeAllOrdersByUser(userToRemove);
            userRepository.deleteUserByUserId(userToRemove.getId());
        }
    }
}