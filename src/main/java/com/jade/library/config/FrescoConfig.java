/*
 * This file provided by Facebook is for non-commercial testing and evaluation purposes only.
 * Facebook reserves all rights not expressly granted.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.jade.library.config;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class FrescoConfig {
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_MEMORY_CACHE_SIZE = 50 * 1024 * 1024;// MAX_HEAP_SIZE / 4;

    public static final int MAX_SMALL_DISK_VERYLOW_CACHE_SIZE = 20 * ByteConstants.MB;// 小图极低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    public static final int MAX_SMALL_DISK_LOW_CACHE_SIZE = 25 * ByteConstants.MB;// 小图低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    public static final int MAX_SMALL_DISK_CACHE_SIZE = 30 * ByteConstants.MB;// 小图磁盘缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）

    public static final int MAX_DISK_CACHE_VERYLOW_SIZE = 25 * ByteConstants.MB;// 默认图极低磁盘空间缓存的最大值
    public static final int MAX_DISK_CACHE_LOW_SIZE = 40 * ByteConstants.MB;// 默认图低磁盘空间缓存的最大值
    public static final int MAX_DISK_CACHE_SIZE = 60 * ByteConstants.MB;// 默认图磁盘缓存的最大值

    private static final String IMAGE_PIPELINE_SMALL_CACHE_DIR = "small_img_cache";// 小图所放路径的文件夹名
    private static final String IMAGE_PIPELINE_CACHE_DIR = "img_cache";// 默认图所放路径的文件夹名

    private static ImagePipelineConfig sImagePipelineConfig;

    public static final int FADE_ANIMATE_TIME = 0; // ms

    private FrescoConfig() {

    }

    public static ImagePipelineConfig getImagePipelineConfig(Context context) {
        if (sImagePipelineConfig == null) {
            sImagePipelineConfig = configureCaches(context);
        }
        return sImagePipelineConfig;
    }

    private static ImagePipelineConfig configureCaches(Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(FrescoConfig.MAX_MEMORY_CACHE_SIZE, // 内存缓存中总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE, // 内存缓存中图片的最大数量。
                FrescoConfig.MAX_MEMORY_CACHE_SIZE, // 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE, // 内存缓存中准备清除的总图片的最大数量。
                Integer.MAX_VALUE); // 内存缓存中单个图片的最大大小。

        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }
        };

        // 小图片的磁盘配置
        DiskCacheConfig diskSmallCacheConfig =
                DiskCacheConfig.newBuilder(context).setBaseDirectoryPath(context.getFilesDir())// 缓存图片基路径
                        .setBaseDirectoryName(IMAGE_PIPELINE_SMALL_CACHE_DIR)// 文件夹名
                        .setMaxCacheSize(FrescoConfig.MAX_DISK_CACHE_SIZE)// 默认缓存的最大大小。
                        .setMaxCacheSizeOnLowDiskSpace(MAX_SMALL_DISK_LOW_CACHE_SIZE)// 缓存的最大大小,使用设备时低磁盘空间。
                        .setMaxCacheSizeOnVeryLowDiskSpace(MAX_SMALL_DISK_VERYLOW_CACHE_SIZE)// 缓存的最大大小,当设备极低磁盘空间
                        .build();

        // 默认图片的磁盘配置
        DiskCacheConfig diskCacheConfig =
                DiskCacheConfig.newBuilder(context).setBaseDirectoryPath(context.getFilesDir())// 缓存图片基路径
                        .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)// 文件夹名
                        .setMaxCacheSize(FrescoConfig.MAX_DISK_CACHE_SIZE)// 默认缓存的最大大小。
                        .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)// 缓存的最大大小,使用设备时低磁盘空间。
                        .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERYLOW_SIZE)// 缓存的最大大小,当设备极低磁盘空间
                        .build();

        // 缓存图片配置
        ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(context)
                // .setAnimatedImageFactory(AnimatedImageFactory
                // animatedImageFactory)//图片加载动画
                .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)// 内存缓存配置（一级缓存，已解码的图片）
                // .setCacheKeyFactory(cacheKeyFactory)//缓存Key工厂
                .setEncodedMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)// 内存缓存和未解码的内存缓存的配置（二级缓存）
                // .setExecutorSupplier(executorSupplier)//线程池配置
                // .setImageCacheStatsTracker(imageCacheStatsTracker)//统计缓存的命中率
                // .setImageDecoder(ImageDecoder imageDecoder) //图片解码器配置
                // .setIsPrefetchEnabledSupplier(Supplier<Boolean>
                // isPrefetchEnabledSupplier)//图片预览（缩略图，预加载图等）预加载到文件缓存
                .setMainDiskCacheConfig(diskCacheConfig)// 磁盘缓存配置（总，三级缓存）
                // .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
                // //内存用量的缩减,有时我们可能会想缩小内存用量。比如应用中有其他数据需要占用内存，不得不把图片缓存清除或者减小
                // 或者我们想检查看看手机是否已经内存不够了。
                // .setNetworkFetchProducer(networkFetchProducer)//自定的网络层配置：如OkHttp，Volley
                // .setPoolFactory(poolFactory)//线程池工厂配置
                // .setProgressiveJpegConfig(progressiveJpegConfig)//渐进式JPEG图
                // .setRequestListeners(requestListeners)//图片请求监听
                // .setResizeAndRotateEnabledForNetwork(boolean
                // resizeAndRotateEnabledForNetwork)//调整和旋转是否支持网络图片
                .setSmallImageDiskCacheConfig(diskSmallCacheConfig)// 磁盘缓存配置（小图片，可选～三级缓存的小图优化缓存）
        ;
        return configBuilder.build();
    }
}
