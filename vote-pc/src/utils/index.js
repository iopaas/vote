import crypto from "crypto";
export const getMD5 = (str) => {
  let md5 = crypto.createHash("md5");
  md5.update(str);
  let a = md5.digest("hex");
  return a;
};
