#import "RNUUIDGenerator.h"

@implementation RNUUIDGenerator

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(getRandomUUID:(RCTResponseSenderBlock)callback)
{
  NSString *uuid = [[NSUUID UUID] UUIDString];

  callback(@[uuid]);
}

RCT_EXPORT_METHOD(getRandomBase64UUID:(BOOL)urlSafe callback:(RCTResponseSenderBlock)callback)
{
  NSUUID *identifier = [NSUUID UUID];
  uuid_t uuid;
  [identifier getUUIDBytes:uuid];
  NSData *originalData = [NSData dataWithBytes:uuid length:16];
  NSString *base64String = [originalData base64EncodedStringWithOptions:NSDataBase64Encoding64CharacterLineLength];
  if (urlSafe) {
    base64String = [base64String stringByReplacingOccurrencesOfString:@"/" withString:@"_"];
    base64String = [base64String stringByReplacingOccurrencesOfString:@"+" withString:@"-"];
    base64String = [base64String stringByReplacingOccurrencesOfString:@"=" withString:@""];
  }
    
  callback(@[base64String]);
}

@end

