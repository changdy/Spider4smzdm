/**
 * Created by changdy on 2017/7/9.
 */
//路径
window.reqBashPath = location.origin + '/';

String.prototype.endTrim = function (char) {
    let length = this.length;
    if (length) {
        if (this.charAt(length - 1) === char) {
            return this.substring(0, length - 1);
        }
    }
    return this.toString();
};