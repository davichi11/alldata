export default {
  'en': {
    modifyTime: 'Last Updated Time',
    modelSetting: 'Model Settings',
    modifiedUser: 'Last Updated By',
    segmentMerge: 'Segment Merge:',
    volatileRange: 'Volatile Range:',
    retention: 'Retention Threshold:',
    newSetting: 'Add Model Setting',
    editSetting: 'Edit Model Setting',
    modelName: 'Model Name',
    settingItem: 'Setting Item',
    autoMerge: 'Auto Merge',
    volatileRangeItem: 'Volatile Range',
    retentionThreshold: 'Retention Threshold',
    HOUR: '1 Hour',
    DAY: '1 Day',
    WEEK: '1 Week',
    MONTH: '1 Month',
    QUARTER: '1 Quarter',
    YEAR: '1 Year',
    hour: 'Hour',
    day: 'Day',
    week: 'Week',
    month: 'Month',
    quarter: 'Quarter',
    year: 'Year',
    addSettingItem: 'Add Setting Item',
    isDel_auto_merge_time_ranges: 'Are you sure delete auto-merge setting item?',
    isDel_volatile_range: 'Are you sure delete volatile range setting item?',
    isDel_retention_range: 'Are you sure you want to delete the "Retention Threshold" setting?',
    'isDel_kylin.engine.spark-conf.spark.executor.cores': 'Are you sure delete spark.executor.cores item?',
    'isDel_kylin.engine.spark-conf.spark.executor.instances': 'Are you sure delete spark.executor.instances item？',
    'isDel_kylin.engine.spark-conf.spark.executor.memory': 'Are you sure delete spark.executor.memory item?',
    'isDel_kylin.engine.spark-conf.spark.sql.shuffle.partitions': 'Are you sure delete spark.sql.shuffle.partitions item?',
    'isDel_kylin.cube.aggrgroup.is-base-cuboid-always-valid': 'Are you sure delete is-base-cuboid-always-valid item?',
    autoMergeTip: 'The system could auto-merge segment fragments over different merging threshold. Auto-merge will optimize storage to enhance query performance.',
    volatileTip: '"Auto-Merge" will not merge the latest segments defined in "Volatile Range". The default value is 0.',
    retentionThresholdDesc: 'The segments within the retention threshold would be kept. The rest would be removed automatically.',
    pleaseSetAutoMerge: 'Please add \'Auto Merge\' setting first.',
    'Auto-merge': 'Auto Merge',
    'Volatile Range': 'Volatile Range',
    'Retention Threshold': 'Retention Threshold',
    'kylin.engine.spark-conf.spark.executor.cores': 'kylin.engine.spark-conf.spark.executor.cores',
    'kylin.engine.spark-conf.spark.executor.instances': 'kylin.engine.spark-conf.spark.executor.instances',
    'kylin.engine.spark-conf.spark.executor.memory': 'kylin.engine.spark-conf.spark.executor.memory',
    'kylin.engine.spark-conf.spark.sql.shuffle.partitions': 'kylin.engine.spark-conf.spark.sql.shuffle.partitions',
    'is-base-cuboid-always-valid': 'is-base-cuboid-always-valid',
    sparkCores: 'The number of cores to use on each executor.',
    sparkInstances: 'The number of executors to use on each application.',
    sparkMemory: 'The amount of memory to use per executor process.',
    sparkShuffle: 'The number of partitions to use when shuffling data for joins or aggregations.',
    baseCuboidConfig: 'According to your business scenario, you can decide whether to add an index that contains dimensions and measures defined in all aggregate groups. The index can answer queries across multiple aggregate groups, but this will impact query performance. In addition to this, there are some storage and building costs by adding this index.',
    customSettings: 'Custom Settings',
    customOptions: 'Besides the defined configurations, you can also add some advanced settings.<br/><i class="el-icon-ksd-alert"></i>Note: It\'s highly recommended to use this feature with the support of Kylin 5 Team.',
    customSettingKeyPlaceholder: 'Configuration Name',
    customSettingValuePlaceholder: 'Value',
    delCustomConfigTip: 'Are you sure you want to delete custom setting item {name}？'
  }
}
