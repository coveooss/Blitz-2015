namespace java com.coveo.blitz.thrift
namespace csharp com.coveo.blitz.thrift

enum OrderingType {
    ASCENDING = 1,
    DESCENDING = 2
}

struct DataPoint {
    /*
     * The dimensions associated with this data point.
     * The key string represents the name of the dimension.
     * The value string represents the value of the dimension.
     */
    1: map<string, string> dimensions

    /*
     * The metrics associated with this data point.
     * The key string represents the name of the metric.
     * The value int represents the value of the metric.
     */
    2: map<string, i64> metrics
}

/*
 * Specifies a filter on a given dimension. All values where the dimension does not have
 * the provided value must be ignored.
 * For example, if we want the results made by the user "John", we'll pass
 * DimensionFilter = { "dimension":USER, "value": "John"}
 */
struct DimensionFilter {
   1: string dimension
   2: string value
}

struct Response {
    /*
     * The list of data points that are part of this response.
     */
    1: list<DataPoint> data
}

/*
 * Sorting information. Either "dimension" or "metric" will be provided.
 */
struct Sort {
    /*
    * The dimension on which to sort. Can be null if sorting by metric.
    */
    1: string dimension
    /*
    * The metric on which to sort. Can be null if sorting by dimension.
    */
    2: string metric
    /*
    * Whether to sort ascending or descending.
    */
    3: OrderingType ordering
}

struct Request {
    1: list<string> dimensions
    2: list<string> metrics
    /*
     * Defines the filters to apply on the request. Can be null or empty.
     * If multiple filters are passed, the rows must match one of the filter.
     * e.g. user is John or user is Bob or verb is GET
     */
    3: list<DimensionFilter> filters
    /*
     * Defines the sort to apply on the request. Can be null.
     */
    4: list<Sort> sorts
}

service AwesomeService {

    /**
     * Gets data from your service. The type and format of the requests are defined in the documentation.
     **/
    Response getData(1: Request request),

    /*
    * Asks your service to reset itself. All data kept in your service must be wiped.
    * If you don't, your responses will be affected and your score.
    */
    void reset()

    /*
    * Ensures that your server is up and running. Simply return "true".
    */
    bool ping()

    /*
    * Tells your service to handle the result of your map reduce. This is called once
    * your map reduce is completed.
    * This can be called more than once with multiple parts of your map reduce.
    * The name parameter provides you with a file name which could be useful if you persist to disk.
    * The data is the result of your map reduce. It is the content of your file.
    */
    void handleMapReduceResult(1: string name, 2: binary data)
}
