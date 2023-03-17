import axios from 'axios';

// const baseURL = 'http://localhost:8080'; // 基础URL
const baseURL = 'http://users.hpnu.cn:28080'; // 基础URL

// 定义API接口
const api = axios.create({
    baseURL: baseURL
});

export const getUserList = (data) => {
    return api.post('/user/list', data);
};

export const getUser = (id) => {
    return api.get(`/user/${id}`);
}

export const updateUser = (data) => {
    return api.put("user", data)
}

export const createUser = (data) => {
    return api.post("user", data)
}

export const removeUser = (id) => {
    return api.delete(`/user/${id}`);
};

export const undoUser = () => {
    return api.get('/undo');
};

export const removeUserBatch = (ids) => {
    return api.post('/user/remove/batch', ids)
};

export const districtChildren = (code) => {
    return api.get(`district/children/${code}`)
}

export const districtAll = () => {
    return api.get(`district/all`)
}

// 更多接口定义...
