import { NativeModules } from 'react-native';

const { RNUUIDGenerator } = NativeModules;

export default {
  getRandomUUID(callback) {
    if (callback) {
      RNUUIDGenerator.getRandomUUID(callback);
    }
    else {
      return new Promise((resolve, reject) => {
        RNUUIDGenerator.getRandomUUID(resolve);
      });
    }
  },

  getRandomBase64UUID(urlSafe, callback) {
    if (callback) {
      RNUUIDGenerator.getRandomBase64UUID(urlSafe, callback);
    }
    else {
      return new Promise((resolve, reject) => {
        RNUUIDGenerator.getRandomBase64UUID(urlSafe, resolve);
      });
    }
  }
};
