[ {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9994172692025323,
      "min-max slowdown" : 1.0016406976846235,
      "average utilization" : 0.43400637159993066,
      "makespan" : 9492.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.43633243940457295,
      "coefficient of variation" : 0.25714002851115264,
      "standard deviation" : 0.11160041076722808,
      "Jain index" : 0.9379798403571982,
      "balancing efficiency" : 0.68149033446874
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.590983999650627,
      "min-max slowdown" : 12.52424245092906,
      "average utilization" : 0.6800722975475342,
      "makespan" : 24068.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5092099351001045,
      "coefficient of variation" : 0.19713638131100739,
      "standard deviation" : 0.13406699176838358,
      "Jain index" : 0.9625910614946079,
      "balancing efficiency" : 0.6840192716540614
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9997324728126357,
      "min-max slowdown" : 1.0006839522511033,
      "average utilization" : 0.3949516827455636,
      "makespan" : 10577.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.40589274495031424,
      "coefficient of variation" : 0.24970358774420237,
      "standard deviation" : 0.09862085216717721,
      "Jain index" : 0.9413076939905707,
      "balancing efficiency" : 0.7037866338429504
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.659841302322467,
      "min-max slowdown" : 7.016559937167942,
      "average utilization" : 0.6469029478897607,
      "makespan" : 25765.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.39468633758758537,
      "coefficient of variation" : 0.23072450486299295,
      "standard deviation" : 0.14925636234627557,
      "Jain index" : 0.9494568088409139,
      "balancing efficiency" : 0.6537272847797896
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9995605040172878,
      "min-max slowdown" : 1.0011179771876737,
      "average utilization" : 0.44982499762634304,
      "makespan" : 9235.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.21152715898186164,
      "standard deviation" : 0.095150203786923,
      "Jain index" : 0.9571725224883142,
      "balancing efficiency" : 0.6965909078358221
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.491912319884018,
      "min-max slowdown" : 10.907219954169411,
      "average utilization" : 0.692730871401889,
      "makespan" : 23987.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.2115271589818616,
      "standard deviation" : 0.1465313931666709,
      "Jain index" : 0.9571725224883142,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9997799006107242,
      "min-max slowdown" : 1.0004623662006484,
      "average utilization" : 0.39371944394647695,
      "makespan" : 10551.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.21152715898186158,
      "standard deviation" : 0.08328235541391657,
      "Jain index" : 0.9571725224883144,
      "balancing efficiency" : 0.6965909078358223
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.667441788059916,
      "min-max slowdown" : 5.585593258440779,
      "average utilization" : 0.688911086746149,
      "makespan" : 24120.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.21152715898186167,
      "standard deviation" : 0.14572340497051975,
      "Jain index" : 0.9571725224883142,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.999631535928015,
      "min-max slowdown" : 1.0009620009620008,
      "average utilization" : 0.4368601437258155,
      "makespan" : 9045.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6566321058276725,
      "coefficient of variation" : 0.09734311947740137,
      "standard deviation" : 0.04252532916561679,
      "Jain index" : 0.990613262835337,
      "balancing efficiency" : 0.847636753664641
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.591176890328758,
      "min-max slowdown" : 11.609569516777638,
      "average utilization" : 0.8685843621399176,
      "makespan" : 18225.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.835621247113164,
      "coefficient of variation" : 0.04477764172027807,
      "standard deviation" : 0.03889315937173749,
      "Jain index" : 0.9979989749316662,
      "balancing efficiency" : 0.877410623556582
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9998479258372915,
      "min-max slowdown" : 1.0004198152812762,
      "average utilization" : 0.3890918462678393,
      "makespan" : 10347.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5864540765056608,
      "coefficient of variation" : 0.1500827287312034,
      "standard deviation" : 0.05839596601493923,
      "Jain index" : 0.9779713656798545,
      "balancing efficiency" : 0.809124405439807
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6792342613124296,
      "min-max slowdown" : 6.278953020371697,
      "average utilization" : 0.908995265151515,
      "makespan" : 17600.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.763858568532668,
      "coefficient of variation" : 0.057863978915575064,
      "standard deviation" : 0.052598082857084824,
      "Jain index" : 0.9966629332446368,
      "balancing efficiency" : 0.9355011305161389
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.44848077138692677,
      "makespan" : 8919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999997,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.5209375000000005,
      "min-max slowdown" : 8.501,
      "average utilization" : 0.9735913350371181,
      "makespan" : 16434.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999998,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.4032664583123299,
      "makespan" : 9919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999994,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.681278125,
      "min-max slowdown" : 4.528,
      "average utilization" : 0.9425625920471281,
      "makespan" : 16975.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 1.0000000000000004,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0486621563227545,
      "min-max slowdown" : 1.6308983014436669,
      "average utilization" : 0.38673710729529936,
      "makespan" : 10466.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.3575263473594392,
      "coefficient of variation" : 0.2574445352021926,
      "standard deviation" : 0.09956335483307885,
      "Jain index" : 0.9378419998772193,
      "balancing efficiency" : 0.6016621724422833
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.607595522560888,
      "min-max slowdown" : 12.064615850453078,
      "average utilization" : 0.7921645746501349,
      "makespan" : 20298.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6608280275252927,
      "coefficient of variation" : 0.12674333697821516,
      "standard deviation" : 0.10040158162708653,
      "Jain index" : 0.9841900948475428,
      "balancing efficiency" : 0.7976981282954971
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0328255524111638,
      "min-max slowdown" : 1.7677024898340155,
      "average utilization" : 0.3480156450152871,
      "makespan" : 11672.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.16003881699645764,
      "coefficient of variation" : 0.5152063377923897,
      "standard deviation" : 0.17929986596278236,
      "Jain index" : 0.7902404854344075,
      "balancing efficiency" : 0.4656874378092508
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6720010650924033,
      "min-max slowdown" : 6.900460562040995,
      "average utilization" : 0.6629770965636812,
      "makespan" : 24585.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.46975962910274044,
      "coefficient of variation" : 0.198334381512525,
      "standard deviation" : 0.13149115240392728,
      "Jain index" : 0.9621522713074808,
      "balancing efficiency" : 0.6702907219754178
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.090230136944283,
      "min-max slowdown" : 2.0154797539303395,
      "average utilization" : 0.392201523863133,
      "makespan" : 10537.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.2024195919781681,
      "standard deviation" : 0.07938927243359115,
      "Jain index" : 0.9606390713210813,
      "balancing efficiency" : 0.6929845820560766
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.496140998650154,
      "min-max slowdown" : 9.313071336436735,
      "average utilization" : 0.8199254828228495,
      "makespan" : 19837.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7054209630553481,
      "coefficient of variation" : 0.11523606541701165,
      "standard deviation" : 0.09448498657564874,
      "Jain index" : 0.9868946793767522,
      "balancing efficiency" : 0.8361923695949898
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9998963091549724,
      "min-max slowdown" : 1.006101042982778,
      "average utilization" : 0.4062508130456206,
      "makespan" : 9713.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.22737275098424037,
      "coefficient of variation" : 0.43328166568702214,
      "standard deviation" : 0.17602102896311353,
      "Jain index" : 0.8419400643639866,
      "balancing efficiency" : 0.5295043420001415
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6608320037894093,
      "min-max slowdown" : 5.249410428725556,
      "average utilization" : 0.7723397789030518,
      "makespan" : 21139.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6494351350012214,
      "coefficient of variation" : 0.1404366388707433,
      "standard deviation" : 0.10846480261531762,
      "Jain index" : 0.9806590023135607,
      "balancing efficiency" : 0.7822077478527537
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0314266909207006,
      "min-max slowdown" : 1.3545878852882864,
      "average utilization" : 0.43686014372581533,
      "makespan" : 9045.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.4755366949575636,
      "coefficient of variation" : 0.25990951068720203,
      "standard deviation" : 0.11354410619451742,
      "Jain index" : 0.9367216834456389,
      "balancing efficiency" : 0.7397778332501247
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.609846755970956,
      "min-max slowdown" : 12.271873283772017,
      "average utilization" : 0.8891232307346664,
      "makespan" : 17804.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8097603190728825,
      "coefficient of variation" : 0.05692359986244661,
      "standard deviation" : 0.05061209501474594,
      "Jain index" : 0.9967701693866321,
      "balancing efficiency" : 0.8934388757196071
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9998479258372915,
      "min-max slowdown" : 1.0004198152812762,
      "average utilization" : 0.3890918462678393,
      "makespan" : 10347.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.20226451457480127,
      "coefficient of variation" : 0.4769207347114079,
      "standard deviation" : 0.1855659691922761,
      "Jain index" : 0.8146948881562377,
      "balancing efficiency" : 0.5819224283305228
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6997365760516643,
      "min-max slowdown" : 6.920636455186304,
      "average utilization" : 0.8053519590569678,
      "makespan" : 19865.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7076606093359453,
      "coefficient of variation" : 0.10294644532359151,
      "standard deviation" : 0.08290812141930544,
      "Jain index" : 0.9895131685269251,
      "balancing efficiency" : 0.8105612038100384
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0730625000000003,
      "min-max slowdown" : 1.705,
      "average utilization" : 0.41562759767248547,
      "makespan" : 9624.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6,
      "coefficient of variation" : 0.07905694150420949,
      "standard deviation" : 0.0328582466767288,
      "Jain index" : 0.9937888198757762,
      "balancing efficiency" : 0.8
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.560021874999996,
      "min-max slowdown" : 10.111,
      "average utilization" : 0.8887407654279842,
      "makespan" : 18003.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7777777777777778,
      "coefficient of variation" : 0.06249999999999999,
      "standard deviation" : 0.055546297839249005,
      "Jain index" : 0.9961089494163426,
      "balancing efficiency" : 0.8888888888888888
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0129625,
      "min-max slowdown" : 1.309,
      "average utilization" : 0.37961469108854506,
      "makespan" : 10537.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.33333333333333337,
      "coefficient of variation" : 0.5000000000000001,
      "standard deviation" : 0.18980734554427256,
      "Jain index" : 0.8000000000000002,
      "balancing efficiency" : 0.6666666666666666
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.735846875000001,
      "min-max slowdown" : 8.396,
      "average utilization" : 0.6649488820546919,
      "makespan" : 24062.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5833333333333333,
      "coefficient of variation" : 0.12500000000000003,
      "standard deviation" : 0.0831186102568365,
      "Jain index" : 0.9846153846153841,
      "balancing efficiency" : 0.6666666666666665
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.4033488214869292,
      "min-max slowdown" : 3.602690258198085,
      "average utilization" : 0.347587987244636,
      "makespan" : 11985.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.1156749354184805,
      "coefficient of variation" : 0.5529465229219602,
      "standard deviation" : 0.19219756895636417,
      "Jain index" : 0.7658434687755318,
      "balancing efficiency" : 0.41853691852905717
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.380325674315914,
      "min-max slowdown" : 19.1941476462807,
      "average utilization" : 0.6898975335148942,
      "makespan" : 23564.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.33270076995993114,
      "coefficient of variation" : 0.27588157471215463,
      "standard deviation" : 0.1903300179361205,
      "Jain index" : 0.9292724742181843,
      "balancing efficiency" : 0.6974220625838781
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.2955540077792764,
      "min-max slowdown" : 3.461483443998995,
      "average utilization" : 0.309897360528354,
      "makespan" : 15425.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.09595275319031424,
      "coefficient of variation" : 0.6210523367110271,
      "standard deviation" : 0.19246247989671386,
      "Jain index" : 0.7216537970097526,
      "balancing efficiency" : 0.3206219983561779
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.558290324242889,
      "min-max slowdown" : 14.583467472607596,
      "average utilization" : 0.4835148632989936,
      "makespan" : 35024.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.1823352225005489,
      "coefficient of variation" : 0.44599190554055645,
      "standard deviation" : 0.21564371523989984,
      "Jain index" : 0.834091814858841,
      "balancing efficiency" : 0.4866165506867617
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.416882970102805,
      "min-max slowdown" : 3.638606085436984,
      "average utilization" : 0.3913528152544322,
      "makespan" : 10765.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.11306009444256081,
      "coefficient of variation" : 0.5338692977959135,
      "standard deviation" : 0.2089312526703376,
      "Jain index" : 0.7782001684088453,
      "balancing efficiency" : 0.4477592681407487
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.249876513497023,
      "min-max slowdown" : 15.764111181895478,
      "average utilization" : 0.6772791573679895,
      "makespan" : 24323.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.31960038008771363,
      "coefficient of variation" : 0.27715298774575375,
      "standard deviation" : 0.18770994200246482,
      "Jain index" : 0.928665680044421,
      "balancing efficiency" : 0.6808829718605085
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.2980336738560618,
      "min-max slowdown" : 3.248632928324939,
      "average utilization" : 0.2867673486453457,
      "makespan" : 16648.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.09452113129546763,
      "coefficient of variation" : 0.6593174373420504,
      "standard deviation" : 0.18907071342222365,
      "Jain index" : 0.6970100789199533,
      "balancing efficiency" : 0.2959856456822412
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.4402475628622278,
      "min-max slowdown" : 10.230458307563781,
      "average utilization" : 0.4804968291388416,
      "makespan" : 35176.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.2185756797215375,
      "coefficient of variation" : 0.44333105813186563,
      "standard deviation" : 0.2130191676911289,
      "Jain index" : 0.8357413639063687,
      "balancing efficiency" : 0.48364172140297945
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.3784888527530137,
      "min-max slowdown" : 3.5145885504629892,
      "average utilization" : 0.38652059082461115,
      "makespan" : 10223.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.14100838146523648,
      "coefficient of variation" : 0.45128883338982134,
      "standard deviation" : 0.17443242651438326,
      "Jain index" : 0.8307982831245578,
      "balancing efficiency" : 0.5147956746428106
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.441932184990956,
      "min-max slowdown" : 21.8198439826118,
      "average utilization" : 0.6054212720388572,
      "makespan" : 26147.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.38350646867631133,
      "coefficient of variation" : 0.25195537285309677,
      "standard deviation" : 0.15253914232974639,
      "Jain index" : 0.9403078386243475,
      "balancing efficiency" : 0.6053132368873877
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.214371000492167,
      "min-max slowdown" : 2.287352594385623,
      "average utilization" : 0.35688999994090154,
      "makespan" : 12534.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.16919100853467964,
      "coefficient of variation" : 0.46562454311490487,
      "standard deviation" : 0.1661767431647607,
      "Jain index" : 0.8218235472079133,
      "balancing efficiency" : 0.40328698695088894
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.49893434091621,
      "min-max slowdown" : 13.212396748312678,
      "average utilization" : 0.5566568081651588,
      "makespan" : 28740.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.29866254404256853,
      "coefficient of variation" : 0.3509209680965057,
      "standard deviation" : 0.1953425460188284,
      "Jain index" : 0.8903565717749574,
      "balancing efficiency" : 0.575189353083579
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.382925,
      "min-max slowdown" : 3.804,
      "average utilization" : 0.4260304611779741,
      "makespan" : 9389.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.14285714285714288,
      "coefficient of variation" : 0.43301270189221946,
      "standard deviation" : 0.18447660108306288,
      "Jain index" : 0.8421052631578945,
      "balancing efficiency" : 0.5714285714285713
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.304759375000001,
      "min-max slowdown" : 18.11,
      "average utilization" : 0.6153136176595007,
      "makespan" : 26003.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.3846153846153846,
      "coefficient of variation" : 0.24286699034656817,
      "standard deviation" : 0.14943936644022188,
      "Jain index" : 0.9443009959424563,
      "balancing efficiency" : 0.6153846153846152
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.22385,
      "min-max slowdown" : 2.3005,
      "average utilization" : 0.3549875754348599,
      "makespan" : 12520.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.16666666666666666,
      "coefficient of variation" : 0.4873397172404481,
      "standard deviation" : 0.17299954463629688,
      "Jain index" : 0.8080808080808088,
      "balancing efficiency" : 0.3703703703703705
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.404265625,
      "min-max slowdown" : 9.8475,
      "average utilization" : 0.5941329372447085,
      "makespan" : 26930.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.38461538461538464,
      "coefficient of variation" : 0.3377314021526574,
      "standard deviation" : 0.2006573499607322,
      "Jain index" : 0.8976157082748948,
      "balancing efficiency" : 0.6153846153846154
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9993620727901191,
      "min-max slowdown" : 1.001695611260586,
      "average utilization" : 0.6146954237548667,
      "makespan" : 9321.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.49098211996255525,
      "coefficient of variation" : 0.20861795954169404,
      "standard deviation" : 0.12823650504335726,
      "Jain index" : 0.9582936671628423,
      "balancing efficiency" : 0.758166600508652
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.689580901629658,
      "min-max slowdown" : 13.36308871234024,
      "average utilization" : 0.9400804402557625,
      "makespan" : 16722.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.9193956564354187,
      "coefficient of variation" : 0.021061815419059552,
      "standard deviation" : 0.01979980071173511,
      "Jain index" : 0.9995565966250209,
      "balancing efficiency" : 0.9588713431800402
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9997095747790918,
      "min-max slowdown" : 1.0008322032924715,
      "average utilization" : 0.6090546171836465,
      "makespan" : 10641.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5044387432292843,
      "coefficient of variation" : 0.22374428391018908,
      "standard deviation" : 0.13627248918394932,
      "Jain index" : 0.9523251691791247,
      "balancing efficiency" : 0.7288772923194461
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.72648104089956,
      "min-max slowdown" : 7.008423451086213,
      "average utilization" : 0.8796363501790493,
      "makespan" : 18128.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8257580940605063,
      "coefficient of variation" : 0.05071268083566816,
      "standard deviation" : 0.04460871747808216,
      "Jain index" : 0.9974348210680664,
      "balancing efficiency" : 0.8928235419231515
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9994901144746576,
      "min-max slowdown" : 1.0011179771876737,
      "average utilization" : 0.6615673625987591,
      "makespan" : 9046.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.4086221018332414,
      "coefficient of variation" : 0.24051985968379924,
      "standard deviation" : 0.1591200892236347,
      "Jain index" : 0.9453137839194204,
      "balancing efficiency" : 0.7032235591659655
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.648969860804804,
      "min-max slowdown" : 10.936108458009105,
      "average utilization" : 0.9610474770306316,
      "makespan" : 16537.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.9253969215339504,
      "coefficient of variation" : 0.022800803857406345,
      "standard deviation" : 0.021912655021430662,
      "Jain index" : 0.9994803934747593,
      "balancing efficiency" : 0.9652131167385785
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9997332948297991,
      "min-max slowdown" : 1.0004623662006484,
      "average utilization" : 0.6106662572202818,
      "makespan" : 10502.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.4977631272019689,
      "coefficient of variation" : 0.20503234873925033,
      "standard deviation" : 0.12520633701368156,
      "Jain index" : 0.9596576579952328,
      "balancing efficiency" : 0.7169386500922386
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.7236268627743936,
      "min-max slowdown" : 5.276531436303107,
      "average utilization" : 0.8803128710565767,
      "makespan" : 18158.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8516917989601552,
      "coefficient of variation" : 0.04812246124471501,
      "standard deviation" : 0.04236282202064392,
      "Jain index" : 0.9976895791300373,
      "balancing efficiency" : 0.8934723647310409
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.999631535928015,
      "min-max slowdown" : 1.0009620009620008,
      "average utilization" : 0.6240859196083077,
      "makespan" : 9045.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.29322173435784854,
      "coefficient of variation" : 0.24162061867652423,
      "standard deviation" : 0.15079202600306688,
      "Jain index" : 0.9448397602936259,
      "balancing efficiency" : 0.77454132037008
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.572518405846834,
      "min-max slowdown" : 11.500393811533053,
      "average utilization" : 0.9582294188861985,
      "makespan" : 16520.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.9295700128202519,
      "coefficient of variation" : 0.019216541389333614,
      "standard deviation" : 0.01841385528850373,
      "Jain index" : 0.9996308608510622,
      "balancing efficiency" : 0.9663997476648826
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9998479258372918,
      "min-max slowdown" : 1.0004198152812762,
      "average utilization" : 0.6484864104463989,
      "makespan" : 10347.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.377254338538319,
      "coefficient of variation" : 0.2439666625700277,
      "standard deviation" : 0.15820906527862508,
      "Jain index" : 0.9438238565805892,
      "balancing efficiency" : 0.7610747728332514
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6601757527225294,
      "min-max slowdown" : 5.872065384180517,
      "average utilization" : 0.9165988694091135,
      "makespan" : 17454.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8576506955177743,
      "coefficient of variation" : 0.04325636802602556,
      "standard deviation" : 0.03964873802739956,
      "Jain index" : 0.9981323811518032,
      "balancing efficiency" : 0.9272594667697063
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.6406868162670383,
      "makespan" : 8919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.2857142857142857,
      "coefficient of variation" : 0.25124689052802224,
      "standard deviation" : 0.16097057038939167,
      "Jain index" : 0.9406231628453853,
      "balancing efficiency" : 0.8163265306122449
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.5209375000000005,
      "min-max slowdown" : 8.501,
      "average utilization" : 0.9735913350371181,
      "makespan" : 16434.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999998,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.6204099358651227,
      "makespan" : 9919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.25,
      "coefficient of variation" : 0.29790938219532476,
      "standard deviation" : 0.18482594070141975,
      "Jain index" : 0.9184845005740523,
      "balancing efficiency" : 0.769230769230769
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.681278125,
      "min-max slowdown" : 4.528,
      "average utilization" : 0.9425625920471281,
      "makespan" : 16975.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 1.0000000000000004,
      "balancing efficiency" : 1.0
    }
  }
} ]