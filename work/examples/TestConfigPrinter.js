{
    simulation : {type : "TestConfigPrinter"},
    servers : 5,
    testName : "test_1",
    taskGenerator :
    { 
        type : "RandomTaskGen",
        tasks : 10,
        maxArrivalTime : 100,
        minExecTime : 5,
        maxExecTime : 15,
        seed : 1
    }
}
