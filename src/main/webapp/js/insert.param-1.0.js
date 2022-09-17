function insertParam(key, value) {
    let queryString = document.location.search;
    let isEmptyParameters = queryString.trim().length === 0;
    if (isEmptyParameters) {
        insertWith("?")
    } else {
        key = encodeURIComponent(key);
        value = encodeURIComponent(value);
        let params = queryString
            .substring(1)
            .split('&');
        let isPresent = false;
        for (let i = 0; i < params.length; i++) {
            if (params[i].startsWith(key + '=')) {
                let pair = params[i].split('=');
                pair[1] = value;
                params[i] = pair.join('=');
                isPresent = true;
                break;
            }
        }
        isPresent
            ? document.location.search = params.join('&')
            : insertWith("&")
    }

    function insertWith(separator) {
        document.location.search += separator + key + "=" + value;
    }
}