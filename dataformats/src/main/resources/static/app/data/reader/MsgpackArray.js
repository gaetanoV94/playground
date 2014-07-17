Ext.define('DF.data.reader.MsgpackArray', {
    extend: 'Ext.data.reader.Array',
    alias: 'reader.msgpackarray',
    
	read: function(response, readOptions) {
        var data, result;

        if (response && response.responseBytes) {
            result = this.getResponseData(response);
			if (result && result.__$isError) {
                return new Ext.data.ResultSet({
                    total  : 0,
                    count  : 0,
                    records: [],
                    success: false,
                    message: result.msg
                });
            } else {
                data = this.readRecords(result, readOptions);
            }
        }

        return data || this.nullResultSet;
    },

    getResponseData: function(response) {
        try {
            return msgpack.unpack(response.responseBytes);
        } catch (ex) {
        console.log(ex);
            Ext.Logger.warn('Unable to parse the CBOR returned by the server');
            return this.createReadError(ex.message);   
        }
    }
});