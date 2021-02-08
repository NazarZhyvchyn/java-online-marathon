package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
    }
    
    private boolean thisContainsUser(User user) {
    	if(users.stream().filter(streamUser -> streamUser.getEmail().equals(user.getEmail())).count()==0) {
    		return false;
    	}
    	return true;
    }
    
    @Override
    public User readUser(String email) {
    	return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User addUser(User user) {
    	if(user == null) return null;
    	if(!thisContainsUser(user)) {
    		users.add(user);
    	}
        return users.stream().filter(streamUser -> streamUser.getEmail().equals(user.getEmail())).findAny().get();
    }

    @Override
    public User updateUser(User user) {
        if(!thisContainsUser(user)) return null;
        User userForUpdating = users.stream().filter(streamUser -> streamUser.getEmail().equals(user.getEmail()))
        		.findFirst().get();
        userForUpdating.setFirstName(user.getFirstName());
        userForUpdating.setLastName(user.getLastName());
        userForUpdating.setMyTodos(user.getMyTodos());
        userForUpdating.setPassword(user.getPassword());
        return userForUpdating;
    }

    @Override
    public void deleteUser(User user) {
        if(thisContainsUser(user)) {
        	for(int i=0;i<users.size();i++) {
        		if(users.get(i).getEmail().equals(user.getEmail())) {
        			users.remove(i);
        			return;
        		}
        	}
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

}
